package org.wouldgo.tictactoe.conf;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.wouldgo.tictactoe.endpoint.conf.WebApiConfigurations;

/**
 * Interface to be implemented in Servlet 3.0+ environments in order to
 * configure the {@link ServletContext} programmatically -- as opposed to (or
 * possibly in conjunction with) the traditional {@code web.xml}-based approach.
 *
 * @see WebAppInitializer
 *
 * @author "wouldgo"
 *
 */
public class WebAppInitializer implements WebApplicationInitializer {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet
	 * .ServletContext)
	 */
	@Override
	public void onStartup(final ServletContext context) throws ServletException {

		try (AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext()) {

			applicationContext.setConfigLocation(WebApiConfigurations.class.getPackage().getName());
			DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);

			ServletRegistration.Dynamic appServlet = context.addServlet("app-servlet", dispatcherServlet);
			appServlet.setAsyncSupported(true);
			appServlet.setLoadOnStartup(1);
			appServlet.addMapping("/*");

			FilterRegistration.Dynamic corsFilter = context.addFilter("cors-filter", CORSFilter.class);
			corsFilter.setAsyncSupported(true);
			corsFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

			context.addListener(new ContextLoaderListener(applicationContext));
		}
	}
}