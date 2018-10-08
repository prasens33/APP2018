package APP2018.REST.Interface;

import javax.ws.rs.*;

import APP2018.REST.Model.Driver;
import APP2018.REST.PATCH;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

public class DriverInterface {

    MongoCollection<Document> collection;

    public DriverInterface() {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("APP18_Workshop4");

        this.collection = database.getCollection("driver");
    }

    public ArrayList<Driver> getAll() {

        ArrayList<Driver> driverList = new ArrayList<Driver>();

        FindIterable<Document> results = collection.find();
        if (results == null) {
            return  driverList;
        }
        for (Document item : results) {
            Driver driver = new Driver(
                    item.getString("firstName"),
                    item.getString("middleName"),
                    item.getString("lastName"),
                    item.getString("emailId"),
                    item.getString("password"),
                    item.getString("phoneNumber"),
                    item.getString("address1"),
                    item.getString("address2"),
                    item.getString("city"),
                    item.getString("state"),
                    item.getString("country"),
                    item.getString("postalCode"),
                    item.getInteger("rating"),
                    item.getInteger("drivingLicenseNumber"),
                    item.getString("dlIssuedState")
            );
            driver.setId(item.getObjectId("_id").toString());
            driverList.add(driver);
        }
        return driverList;
    }


    public Driver getOne(String id) {


        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));

        Document item = collection.find(query).first();
        if (item == null) {
            return  null;
        }
        Driver driver = new Driver(
                item.getString("firstName"),
                item.getString("middleName"),
                item.getString("lastName"),
                item.getString("emailId"),
                item.getString("password"),
                item.getString("phoneNumber"),
                item.getString("address1"),
                item.getString("address2"),
                item.getString("city"),
                item.getString("state"),
                item.getString("country"),
                item.getString("postalCode"),
                item.getInteger("rating"),
                item.getInteger("drivingLicenseNumber"),
                item.getString("dlIssuedState")
        );
        driver.setId(item.getObjectId("_id").toString());
        return driver;

    }


    public Object create(JSONObject obj) {
        try {
            Document doc = new Document("firstName", obj.getString("firstName"))
                    .append("middleName", obj.getString("middleName"))
                    .append("lastName", obj.getString("lastName"))
                    .append("emailId", obj.getString("emailId"))
                    .append("password", obj.getString("password"))
                    .append("phoneNumber", obj.getString("phoneNumber"))
                    .append("address1", obj.getString("address1"))
                    .append("address2", obj.getString("address2"))
                    .append("city", obj.getString("city"))
                    .append("state", obj.getString("state"))
                    .append("country", obj.getString("country"))
                    .append("postalCode", obj.getString("postalCode"))
                    .append("rating", obj.getInt("rating"))
                    .append("drivingLicenseNumber", obj.getInt("drivingLicenseNumber"))
                    .append("dlIssuedState", obj.getString("dlIssuedState"))
                    ;

            collection.insertOne(doc);

        } catch(JSONException e) {

        }
        return obj;
    }



    public Object update(String id, JSONObject obj) {
        try {

            BasicDBObject query = new BasicDBObject();
            query.put("_id", new ObjectId(id));

            Document doc = new Document();
            if (obj.has("firstName"))
                doc.append("firstName",obj.getString("firstName"));
            if (obj.has("middleName"))
                doc.append("middleName",obj.getString("middleName"));
            if (obj.has("lastName"))
                doc.append("lastName",obj.getString("lastName"));
            if (obj.has("emailId"))
                doc.append("emailId",obj.getString("emailId"));
            if (obj.has("password"))
                doc.append("password",obj.getString("password"));
            if (obj.has("phoneNumber"))
                doc.append("phoneNumber",obj.getString("phoneNumber"));
            if (obj.has("address1"))
                doc.append("address1",obj.getString("address1"));
            if (obj.has("address2"))
                doc.append("address2",obj.getString("address2"));
            if (obj.has("city"))
                doc.append("city",obj.getString("city"));
            if (obj.has("state"))
                doc.append("state",obj.getString("state"));
            if (obj.has("country"))
                doc.append("country",obj.getString("country"));
            if (obj.has("postalCode"))
                doc.append("postalCode",obj.getString("postalCode"));
            if (obj.has("rating"))
                doc.append("rating",obj.getInt("rating"));
            if (obj.has("drivingLicenseNumber"))
                doc.append("drivingLicenseNumber",obj.getInt("drivingLicenseNumber"));
            if (obj.has("dlIssuedState"))
                doc.append("dlIssuedState",obj.getString("dlIssuedState"));


            Document set = new Document("$set", doc);
            collection.updateOne(query,set);

        } catch(JSONException e) {
            System.out.println("Failed to create a document");

        }
        return obj;
    }



    public Object delete(String id) {
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));

        collection.deleteOne(query);

        return new JSONObject();
    }


}