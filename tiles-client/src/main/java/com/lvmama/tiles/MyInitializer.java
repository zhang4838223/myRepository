package com.lvmama.tiles;

import org.apache.tiles.extras.complete.CompleteAutoloadTilesInitializer;
import org.apache.tiles.factory.AbstractTilesContainerFactory;
import org.apache.tiles.request.ApplicationContext;

public class MyInitializer extends CompleteAutoloadTilesInitializer {
	/**
	 * 使我们自定义的容器工厂生效
	 */
	@Override
	protected AbstractTilesContainerFactory createContainerFactory(
			ApplicationContext context) {
		// TODO Auto-generated method stub
		return new MyTilesFactory();
	}
}
