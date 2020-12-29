package ro.tuc.ds2020;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import ro.tuc.ds2020.rmi.IMedPlanRMI;

import java.util.TimeZone;

@SpringBootApplication
public class Ds2020RpcApplication {

	@Bean
	RmiProxyFactoryBean rmiProxy() {
		RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
		bean.setServiceInterface(IMedPlanRMI.class);
		bean.setServiceUrl("rmi://localhost:1099/medplanrmi");

		return bean;
	}

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		IMedPlanRMI helloWorldRMI = SpringApplication.run(Ds2020RpcApplication.class, args).getBean(IMedPlanRMI.class);

		System.out.println(helloWorldRMI.sayHelloRmi("paul"));
	}

}
