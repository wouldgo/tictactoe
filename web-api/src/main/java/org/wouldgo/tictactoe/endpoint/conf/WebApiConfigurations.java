package org.wouldgo.tictactoe.endpoint.conf;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.wouldgo.tictactoe.conf.MiddleWareConfiguration;
import org.wouldgo.tictactoe.controllers.ExceptionControllerAdvice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk7.Jdk7Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;

@Configuration
@ComponentScan(basePackageClasses = ExceptionControllerAdvice.class)
@Import(MiddleWareConfiguration.class)
@EnableWebMvc
public class WebApiConfigurations extends WebMvcConfigurerAdapter {

	@Bean
	public static ObjectMapper objectMapper() {
		final ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JodaModule());
		objectMapper.registerModule(new Jdk7Module());
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		return objectMapper;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
	 * #configureDefaultServletHandling(org.springframework.web.servlet.config.
	 * annotation.DefaultServletHandlerConfigurer)
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

		configurer.enable();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
	 * #configureMessageConverters(java.util.List)
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

		converter.setObjectMapper(WebApiConfigurations.objectMapper());
		converters.add(converter);
		super.configureMessageConverters(converters);
	}
}