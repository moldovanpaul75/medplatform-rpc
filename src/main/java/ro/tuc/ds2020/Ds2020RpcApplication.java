package ro.tuc.ds2020;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.presentation.MedicationDispenserWindow;
import ro.tuc.ds2020.rmi.IMedPlanRMI;

import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

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
		//TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		SpringApplicationBuilder builder = new SpringApplicationBuilder(Ds2020RpcApplication.class);
		builder.headless(false);
		IMedPlanRMI medPlanRMI = builder.run(args).getBean(IMedPlanRMI.class);

		MedicationDispenserWindow.newMedDispenserWindow(medPlanRMI);
	}

}
