package com.zavod;

import com.zavod.repository.ZigRepository;
import com.zavod.util.AuthenticationUtilities;
import org.apache.logging.log4j.util.Strings;
import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XUpdateQueryService;

import javax.annotation.PostConstruct;
import java.io.IOException;

import static com.zavod.util.XUpdateTemplate.APPEND;


@SpringBootApplication
@EnableWebMvc
public class ZigApplication {

	@Autowired
	private ZigRepository zigRepository;
	public static void main(String[] args) {
		SpringApplication.run(ZigApplication.class, args);
	}
	@PostConstruct
	public void init() throws Exception {
		zigRepository.load();
//		run(AuthenticationUtilities.loadProperties(), Strings.EMPTY_ARRAY);
	}



}
