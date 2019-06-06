package vertx.example.crud;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class EmployeeVerticle extends AbstractVerticle{
	
	public static void main (String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new EmployeeVerticle());
	}
	
	@Override
	public void start throws Exception{
		Router router = Router.router(vertx);
		EmployeeResource er = new EmployeeResource();
		Router employeeRoute = er.getEmployeeRouter(vertex);
		router.mountSubRouter("/",employeeRoute);
		vertx.createHttpServer().requestHandler(router::accept).listen(8822);
	}

}
