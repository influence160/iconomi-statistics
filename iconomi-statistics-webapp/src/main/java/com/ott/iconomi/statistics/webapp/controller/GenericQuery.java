package com.ott.iconomi.statistics.webapp.controller;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.ApplicationScope;

import com.ott.iconomi.statistics.webapp.WebappProperties;
import com.ott.iconomi.statistics.webapp.domain.BuiltInQuery;

@Controller
@RequestMapping(value = "/query")
@ApplicationScope
public class GenericQuery {
	
	private JdbcTemplate jdbcTemplate;
	private WebappProperties webappProperties;

	Query query = new Query();
	
	public GenericQuery(DataSource dataSource, WebappProperties webappProperties) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		this.webappProperties = webappProperties;
	}
	
	public static class Query {
		private String selectedQuery;
		private String queryString;
		private List<Map<String, Object>> result;

		public String getSelectedQuery() {
			return selectedQuery;
		}

		public void setSelectedQuery(String selectedQuery) {
			this.selectedQuery = selectedQuery;
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

    @ModelAttribute("module")
    String module() {
        return "home";
    }
    
    @ModelAttribute("query")
    public Query getQuery() {
    	return query;
    }
    
    @ModelAttribute("builtInQueries")
    public List<BuiltInQuery> getBuiltInQueries() {
    	return webappProperties.getQueries();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String query() {
        return "query/query";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String execute(@ModelAttribute Query query, Model model) {
    	List<Map<String, Object>> result = jdbcTemplate.queryForList(query.getQueryString());
    	query.setResult(result);
        return "query/query";
    }
}
