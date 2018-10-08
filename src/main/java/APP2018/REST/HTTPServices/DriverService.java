package APP2018.REST.HTTPServices;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import APP2018.REST.Interface.DriverInterface;
import APP2018.REST.Model.Driver;
import APP2018.REST.PATCH;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;


@Path("driver")
public class DriverService {
    DriverInterface accessPoint = new DriverInterface();

    //GET Drivers - All
    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    public ArrayList<Driver> getDrivers() {
        ArrayList<Driver> driverList = new ArrayList<Driver>();
        driverList = accessPoint.getAll();

        return driverList;
    }

    //GET Driver - Single
    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public Driver getDriver(@PathParam("id") String id) {

        return accessPoint.getOne(id);

    }

    //POST a Driver
    @POST
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public Object createDriver(JSONObject obj) {

        return accessPoint.create(obj);

    }

    //PATCH a Driver
    @PATCH
    @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public Object updateDriver(@PathParam("id") String id, JSONObject obj) {

        return accessPoint.update(id,obj);

    }


    //DELETE a Driver
    @DELETE
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public Object deleteDriver(@PathParam("id") String id) {

        return accessPoint.delete(id);

    }





}
