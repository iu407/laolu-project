package com.prs.jy.converter;

import java.util.Iterator;

import com.prs.jy.model.FiveEightCity.ProductData;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class CityProductConverter implements Converter {

	@Override
	public void marshal(Object object, HierarchicalStreamWriter writer,
			MarshallingContext marshallingContext) {

	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		ProductData productData = new ProductData();
		String nodename = reader.getNodeName();
		System.out.println(reader.getAttribute(nodename));
		while(reader.hasMoreChildren()) {
			nodename = reader.getNodeName();
			System.out.println(reader.getAttribute(nodename));
			reader.moveDown();
			System.out.println(reader.getAttribute("id"));
			System.out.println(reader.getAttribute("name"));
			reader.moveUp();
		}
		
		return productData;
	}

	@Override
	public boolean canConvert(Class clazz) {
		return clazz.equals(ProductData.class);
	}

}
