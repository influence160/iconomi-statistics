package com.ott.iconomi.statistics.core.entity;

import org.junit.jupiter.api.Test;

import com.ott.iconomi.statistics.data.entity.CurrentStructureEntity;

public class CurrentStructureTest {
	
	@Test
	public void testNewCurrentStructure() {
		CurrentStructureEntity currentStructure = new CurrentStructureEntity();
		currentStructure.setDayReturn(-0.53f);
		currentStructure.setAllTimeReturn(156.33f);
		System.out.println(currentStructure.getDayReturn());
		System.out.println(currentStructure.getAllTimeReturn());
	}

}
