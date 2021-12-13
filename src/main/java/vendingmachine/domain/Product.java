package vendingmachine.domain;

import static vendingmachine.resource.MessageResource.*;

import vendingmachine.utils.NumericUtils;

public class Product {
	private static final int MIN_AMOUNT = 100;
	private static final int DIVIDE_NUM = 10;

	private final String name;
	private final int price;
	private int count;

	public Product(String name, int price, int count) {
		check(price);
		this.name = name;
		this.price = price;
		this.count = count;
	}

	public Product(String[] split) {
		this(split[0], NumericUtils.parsePositiveInt(split[1]), NumericUtils.parsePositiveInt(split[2]));
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public void reduceCount() {
		count--;
	}

	private void check(int price) {
		if (price < MIN_AMOUNT || price % DIVIDE_NUM != 0) {
			throw new IllegalArgumentException(ERROR_PRODUCT_PRICE_NOT_VALID);
		}
	}
}
