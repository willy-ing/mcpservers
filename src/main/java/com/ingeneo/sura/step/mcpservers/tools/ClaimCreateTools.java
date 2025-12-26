package com.ingeneo.sura.step.mcpservers.tools;

import com.ingeneo.sura.step.mcpservers.claimRequest.*;
import com.ingeneo.sura.step.mcpservers.claimResponse.CreateClaimResponse;
import com.ingeneo.sura.step.mcpservers.dto.DatosIncidente;
import com.ingeneo.sura.step.mcpservers.dto.EstimacionPerdida;
import com.ingeneo.sura.step.mcpservers.dto.Persona;
import com.ingeneo.sura.step.mcpservers.dto.Vehiculo;
import com.ingeneo.sura.step.mcpservers.validators.ValidationException;
import com.ingeneo.sura.step.mcpservers.validators.Validator;
import com.ingeneo.sura.step.mcpservers.validators.ValidatorFactory;
import com.ingeneo.sura.step.mcpservers.validators.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class ClaimCreateTools {

    private CreateClaimRequest createClaimRequest;

    private final RestClient restClient;

    private static final Logger log = LoggerFactory.getLogger(ClaimCreateTools.class);

    public ClaimCreateTools() {

        String grantedAuthorities = "{\"id\":1,\"username\":\"userAsisWSClient\",\"securityRealm\":{\"id\":1,\"realm\":\"default\"},\"grantedAuthorities\":[{\"id\":1,\"target\":\"54-123456\",\"authorityType\":\"POLICY\"}]}";
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8080/cc/service/edge/sura")
                .defaultHeader("Authorization", "Basic dXNlck1hY2FXU0NsaWVudDp1c2VyTWFjYVdTQ2xpZW50")
                .defaultHeader("usertoken", "userAsisWSClient")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Granted-Authorities", grantedAuthorities)
                .build();

        createClaimRequest = new CreateClaimRequest();
        createClaimRequest.setId("1");
        createClaimRequest.setMethod("createClaim");
        createClaimRequest.setJsonrpc("2.0");
    }

    @Tool(name = "collect_incident_data", description = "Obtiene los datos principales del siniestro especificamente la poliza, la fecha del siniestro, la hora del siniestro y el tipo de siniestro")
    public String createDataIncident(@ToolParam DatosIncidente datosIncidente) {
        Validator<DatosIncidente> validator = ValidatorFactory.datosIncidenteValidator();
        try {
            validator.validate(datosIncidente);
        }catch (ValidationException ve){
            return "Error de validación: " + ve.getMessage();
        }
        ClaimData claimData = new ClaimData();
        claimData.setLossDate(DateUtil.converDate(datosIncidente.fechaSiniestro() ));
        claimData.setNotificationDate(DateUtil.converDate(new Date()));
        claimData.setLossType(datosIncidente.tipoSiniestro());

        //TODO mejorar el mapeo del tipo de siniestro
        claimData.setAuthorityTransit("TA");
        claimData.setIsSuspect(false);
        claimData.setDescription(datosIncidente.descripcion());
        claimData.setMacaNumber("MACA-1622");
        claimData.setFaultRating("5");
        claimData.setAuthorUser("LUZEVAMO");
        claimData.setSegment("auto_high");
        claimData.setLossCause("hurtoparcial");
        claimData.setOriginCause("atraco");
        LossLocation lossLocation = new LossLocation();
        lossLocation.setAddressLine1("Calle 32a #45-67");
        lossLocation.setCity("05001");
        lossLocation.setCountry("CO");
        claimData.setLossLocation(lossLocation);

        createClaimRequest.setParams(List.of(datosIncidente.numeroPoliza(), claimData));
        log.info("Descripcion1: " + claimData.getDescription());
        return "Datos del siniestro validados correctamente: " + datosIncidente.toString();
    }

    @Tool(name = "collect_reporter_data", description = "Obtiene los datos principales del que reporta la reclamacion especificamente los nombres separados, los datos de telefono, cedula, email y direccion")
    public String createMainContact(@ToolParam Persona persona) {
        Validator<Persona> validator = ValidatorFactory.datosPersona();
        try {
            validator.validate(persona);
        }catch (ValidationException ve){
            return "Error de validación: " + ve.getMessage();
        }
        ClaimData claimData = (ClaimData) createClaimRequest.getParams().get(1);
        MainContact mainContact = new MainContact();
        mainContact.setFirstName(persona.primerNombre());
        mainContact.setLastName(persona.primerApellido());
        mainContact.setCellNumber(persona.numeroTelefono());
        mainContact.setDocumentType(persona.tipoIdentificacion());
        mainContact.setTaxID(persona.numeroIdentificacion());
        mainContact.setEmailAddress1(persona.correoElectronico());
        PrimaryAddressMainContact primaryAddressMainContact = new PrimaryAddressMainContact();
        primaryAddressMainContact.setAddressLine1("Calle 25 #16-45");
        primaryAddressMainContact.setAddressType("HOME");
        primaryAddressMainContact.setCity("05001000");
        mainContact.setPrimaryAddressMainContact(primaryAddressMainContact);

        claimData.setMainContact(mainContact);

        log.info("Descripcion2:" + claimData.getDescription());

        return "Datos del siniestro validados correctamente: " + persona.toString();
    }

    @Tool(name = "collect_vehicle_data", description = "Obtiene los datos vehiculo, como placa, modelo, marca, color y tipo de vehiculo")
    public String createDriver(@ToolParam Vehiculo vehiculo) {
        Validator<Vehiculo> validator = ValidatorFactory.datosVehiculo();
        try {
            validator.validate(vehiculo);
        }catch (ValidationException ve){
            return "Error de validación: " + ve.getMessage();
        }
        ClaimData claimData = (ClaimData) createClaimRequest.getParams().get(1);
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(vehiculo.placa());
        vehicle.setYear(Integer.parseInt(vehiculo.ano()));
        vehicle.setModel(vehiculo.modelo());
        vehicle.setMake(vehiculo.marca());
        vehicle.setVehicleType("1");
        vehicle.setColor(vehiculo.color());
        vehicle.setFasecoldaCode("05606086");
        vehicle.setVin(vehicle.getVin());

        PersonalAuto personalAuto = new PersonalAuto();
        personalAuto.setVehicleIncidents(List.of(new VehicleIncident()));

        VehicleIncident vehicleIncident = personalAuto.getVehicleIncidents().get(0);
        vehicleIncident.setVehicle(vehicle);

        LobsData lobsData = new LobsData();
        lobsData.setPersonalAuto(personalAuto);
        claimData.setLobs(lobsData);

        log.info("Descripcion3:" + claimData.getDescription());

        return "Datos del siniestro validados correctamente: " + vehiculo.toString();
    }

    @Tool(name = "collect_driver_data", description = "Obtiene los datos principales del conductor del vehiculo asociado a la reclamacion especificamente los nombres separados, los datos de telefono, cedula, email y direccion")
    public String createDriver(@ToolParam Persona persona) {
        Validator<Persona> validator = ValidatorFactory.datosPersona();
        try {
            validator.validate(persona);
        }catch (ValidationException ve){
            return "Error de validación: " + ve.getMessage();
        }
        ClaimData claimData = (ClaimData) createClaimRequest.getParams().get(1);
        Driver driver = new Driver();
        driver.setFirstName(persona.primerNombre());
        driver.setLastName(persona.primerApellido());
        driver.setCellNumber(persona.numeroTelefono());
        driver.setDocumentType(persona.tipoIdentificacion());
        driver.setTaxID(persona.numeroIdentificacion());
        driver.setEmailAddress1(persona.correoElectronico());
        PrimaryAddressDriver primaryAddressDriver = new PrimaryAddressDriver();
        primaryAddressDriver.setAddressLine1("Calle 25 #16-45");
        primaryAddressDriver.setAddressType("HOME");
        primaryAddressDriver.setCity("00501");
        driver.setPrimaryAddressDriver(primaryAddressDriver);

        PersonalAuto personalAuto = claimData.getLobs().getPersonalAuto();
        VehicleIncident vehicleIncident = personalAuto.getVehicleIncidents().get(0);
        vehicleIncident.setDriver(driver);

        log.info("Descripcion4:" + claimData.getDescription());

        return "Datos del siniestro validados correctamente: " + persona.toString();
    }

    @Tool(name = "collect_loss_estimate_data", description = "Obtiene los datos principales del monto de la reclamacion y la moneda")
    public String createLossestimate(@ToolParam EstimacionPerdida estimacionPerdida) {
        ClaimData claimData = (ClaimData) createClaimRequest.getParams().get(1);
        LossEstimate lossEstimate = new LossEstimate();
        lossEstimate.setAmount(Long.parseLong(estimacionPerdida.montoReclamacion()));
        lossEstimate.setCurrency("cop");
        claimData.setLossEstimate(lossEstimate);

        return "Datos del siniestro validados correctamente: " + estimacionPerdida.toString();
    }

    @Tool(name = "end_sending", description = "envia los datos de la reclamacion al sistema Claim Center")
    public String createClaim() {
        ClaimData claimData = (ClaimData) createClaimRequest.getParams().get(1);

        PersonalAuto personalAuto = claimData.getLobs().getPersonalAuto();
        VehicleIncident vehicleIncident = personalAuto.getVehicleIncidents().get(0);
        vehicleIncident.setDescription(claimData.getDescription());

        vehicleIncident.setCollision(false);
        vehicleIncident.setLossParty("insured");
        vehicleIncident.setDriverRelation("self");
        vehicleIncident.setMovePermission(false);
        vehicleIncident.setRepairShop("ab:301");
        vehicleIncident.setPassengers(new ArrayList<>());

        log.info("✓ Request válido - enviando al servicio...");

        // Hacer el llamado al servicio
        String username = "su";
        String password = "gw";
        String auth = username + ":" + password;
        String base64Creds = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

        var claim = restClient.post()
                .uri("/claim")
                .header(HttpHeaders.AUTHORIZATION, "Basic " + base64Creds)
                .contentType(MediaType.APPLICATION_JSON)
                .body(createClaimRequest)
                .retrieve()
                .body(CreateClaimResponse.class);

        if (claim != null) {
            String claimText = String.format("Numero de reclamación: %s", claim.getCreateClaim().getResult().getClaimNumber());
            return claimText;
        } else {
            return "No se creo la reclamación.";
        }
    }
}
