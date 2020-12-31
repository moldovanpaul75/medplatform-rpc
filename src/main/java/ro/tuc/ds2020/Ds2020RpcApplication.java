package ro.tuc.ds2020;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.googlecode.jsonrpc4j.IJsonRpcClient;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.jsonrpc.IMedPlanRPC;
import ro.tuc.ds2020.presentation.MedicationDispenserWindow;
import ro.tuc.ds2020.rmi.IMedPlanRMI;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@SpringBootApplication
public class Ds2020RpcApplication {


	private static final String SERVER_ENDPOINT = "https://medplatform-backend.herokuapp.com/medplanrpc"; //http://localhost:8080/medplanrpc

//	@Bean
//	RmiProxyFactoryBean rmiProxy() {
//		RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
//		bean.setServiceInterface(IMedPlanRMI.class);
//		bean.setServiceUrl("rmi://localhost:1099/medplanrmi");
//
//		return bean;
//	}

	public static void main(String[] args) throws MalformedURLException {
		//TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		SpringApplicationBuilder builder = new SpringApplicationBuilder(Ds2020RpcApplication.class);
		builder.headless(false);

		IJsonRpcClient client = new JsonRpcHttpClient(new URL(SERVER_ENDPOINT));
		IMedPlanRPC service = ProxyUtil.createClientProxy(Ds2020RpcApplication.class.getClassLoader(),IMedPlanRPC.class,client);


		//IMedPlanRMI medPlanRMI = builder.run(args).getBean(IMedPlanRMI.class);

		MedicationDispenserWindow.newMedDispenserWindow(service);
	}

}
