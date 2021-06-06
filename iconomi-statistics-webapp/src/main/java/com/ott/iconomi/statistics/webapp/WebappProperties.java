package com.ott.iconomi.statistics.webapp;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.ott.iconomi.statistics.webapp.domain.BuiltInQuery;

@Component
@ConfigurationProperties(prefix = "iconomi.statistics.webapp")
public class WebappProperties {

	private List<BuiltInQuery> queries;

	public List<BuiltInQuery> getQueries() {
		return queries;
	}

	public void setQueries(List<BuiltInQuery> queries) {
		this.queries = queries;
	}
	
	
}
