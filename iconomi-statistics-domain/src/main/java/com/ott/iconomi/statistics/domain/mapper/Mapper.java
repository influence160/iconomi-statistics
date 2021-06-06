package com.ott.iconomi.statistics.domain.mapper;

public interface Mapper<D, E> {
	
	public D toDomain(E e);
	
	public E toEntity(D d);

}
