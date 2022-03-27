package com.ott.iconomi.statistics.webapp.controller;

import com.ott.iconomi.statistics.webapp.WebappProperties;
import com.ott.iconomi.statistics.webapp.domain.BuiltInPreparedQuery;
import com.ott.iconomi.statistics.webapp.domain.BuiltInQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/prepared-query")
@ApplicationScope
public class GenericPreparedQuery {

	public static class Query {
		private String selectedQuery;
		private String queryString;
		private Map<String, String> arguments;
		private List<Map<String, Object>> result;

		public String getSelectedQuery() {
			return selectedQuery;
		}

		public void setSelectedQuery(String selectedQuery) {
			this.selectedQuery = selectedQuery;
		}

		public Map<String, String> getArguments() {
			return arguments;
		}

		public void setArguments(Map<String, String> arguments) {
			this.arguments = arguments;
		}

		public String getQueryString() {
			return queryString;
		}

		public void setQueryString(String queryString) {
			this.queryString = queryString;
		}

		public List<Map<String, Object>> getResult() {
			return result;
		}

		public void setResult(List<Map<String, Object>> result) {
			this.result = result;
		}
	}

	private JdbcTemplate jdbcTemplate;
	private WebappProperties webappProperties;

	Query query = new Query();

	public GenericPreparedQuery(DataSource dataSource, WebappProperties webappProperties) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		this.webappProperties = webappProperties;
	}

    @ModelAttribute("query")
    public Query getQuery() {
    	return query;
    }
    
    @ModelAttribute("builtInPreparedQueries")
    public List<BuiltInPreparedQuery> getBuiltInPreparedQueries() {
    	return webappProperties.getPreparedQueries();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String execute(@ModelAttribute Query query, Model model) {
		if (query.getQueryString() != null) {
			List<Map<String, Object>> result = jdbcTemplate.queryForList(query.getQueryString());
			query.setResult(result);
		}
        return "query/prepared-query";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/arguments")
	@ResponseBody
	public List<BuiltInPreparedQuery.Argument> getArguments(@RequestParam("query") String queryLabel) {
		//TODO
		return null;
	}
}
