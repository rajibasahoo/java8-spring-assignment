package vertx.example.crud;

import java.util.List;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.handler.BodyHandler;

public class EmployeeResource {
	
	private SQLClient postgreclient;
	Vertx vertx;
	
	public Router getEmployeeRouter(Vertx vertx) {
		this.vertx=vertx;
		JsonObject dbconfig = configureDB();
		postgreclient=postgreclient.createShared(vertx,dbconfig);
		Router router = Router.router(vertx);
		
		router.route("v1/employees*").handler(BodyHandler.create());
		router.get("v1/employees").handler(this::getAllEmployees);
		router.get("v1/employees/:id").handler(this::getAllEmployeesById);
		router.delete("v1/employees/:id").handler(this::deleteEmployeesById);
		router.post("v1/employees/:id").handler(this::addEmployee);
		router.post("v1/employees/:id").handler(this::updateEmployeeById);
		
		return router;
	}
	
	private JSONObject configureDB() {
		JsonObject dbconfig = new JSONObject();
		dbconfig.put("host", "localhost/postgre");
		dbconfig.put("port", 4444);
		dbconfig.put("database", "cloud");
		dbconfig.put("username", "admin");
		dbconfig.put("password", "123@#");
		return dbconfig;
	}

	public void getAllEmployees(RoutingContext rc) {
		postgreclient.getConnection(ar-> {
			
			SQLConnection con = ar.result();
			con.query("select * from employee",result -> {
			List<Employee> emp = result.result().getRows.stream()
					.map(Employee::new).collect(Collectors.toList());
			
			rc.response(setStatusCode(200).putHeader("content-type"),"application/json;charset-utf8")
			.end(Json.encodePrettily(emp));
			connection.close();
			});
		});
	}

	public void getAllEmployeeById(RoutingContext rc) {
		String sql = "select * from emp where id=?";
		postgreclient.getConnection(ar-> {
			SQLConnection con = ar.result();
			con.queryWithParam(sql,newJsonArray().add(rc.request().getParam(id))),result-> {
			List<Employee> employee = result.result().getRows.stream().map(Employee::new).(collect(Collectors.toList()));
			rc.response().setStatus(200).putHead().end(JSONencode(emp);
			con.close();
			}
		}
	}
	
	public void addEmployee(RoutingContext rc) {
		postgreclient.getConnection(ar->{
			final Employee emp = Json.decodeValue(rc.getBodyAsString(), Employee.class);
			SQLConnection connection =ar.result();
			insert(emp,connection,(r)->rc.response().setStatuscode(201).putHeader("conn").end(JSON.encodePretty(r.result())));
			connection.close();
		})
	}
		
	private insert(Employee emp,SQLConnection con,Handler<AsyncResult<Employee>> next)
	{
		String sql ="insert into employee(id,name) values (?,?)";
		connection.updatewithParam(sql,new Jsonarray.add(employee.getId.add(emp.getName))),(ar)->{
			if(ar.failed()) {
				next.handle(failure.failedfuture(ar.cause));
				connection.close();
				return ;
			}
			UpdateResult result = ar.result.next.handle(Future.successed);
		});
	}
	
	public void deleteEmployeesById(RoutingContext rc) {
		postgreclient.getConnection(ar->{
			final Employee emp = Json.decodeValue(rc.getBodyAsString(), Employee.class);
			SQLConnection connection =ar.result();
			insert(emp,connection,(r)->rc.response().setStatuscode(201).putHeader("conn").end(JSON.encodePretty(r.result())));
			connection.close();
		})
	}
	
	public void updateEmployeeById(RoutingContext rc) {
		postgreclient.getConnection(ar->{
			final Employee emp = Json.decodeValue(rc.getBodyAsString(), Employee.class);
			SQLConnection connection =ar.result();
			insert(emp,connection,(r)->rc.response().setStatuscode(201).putHeader("conn").end(JSON.encodePretty(r.result())));
			connection.close();
		})
	}
	
}
