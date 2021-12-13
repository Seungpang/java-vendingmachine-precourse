package vendingmachine.domain;

import static vendingmachine.resource.MessageResource.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Products {
	private static final int PRODUCT_ARRAY_LENGTH = 3;
	private static final String SEMICOLON = ";";
	private static final String COMMA = ",";
	private static final String LEFT_SQUARE_BRACKETS = "[";
	private static final String RIGHT_SQUARE_BRACKETS = "]";

	private final List<Product> products;

	public Products(String inputProducts) {
		this.products = createProducts(inputProducts);
	}

	private List<Product> createProducts(String inputProducts) {
		String[] split = inputProducts.split(SEMICOLON);
		checkFormat(split);

		return Arrays.stream(split)
			.map(str -> new Product(getProductSplit(str)))
			.collect(Collectors.toList());
	}

	private String[] getProductSplit(String str) {
		String[] item = str.substring(1, str.length() - 1).split(COMMA);
		if (item.length != PRODUCT_ARRAY_LENGTH) {
			throw new IllegalArgumentException(ERROR_INPUT_PRODUCT_NOT_VALID);
		}
		return item;
	}

	private void checkFormat(String[] split) {
		for (String s : split) {
			if (!(s.startsWith(LEFT_SQUARE_BRACKETS) && s.endsWith(RIGHT_SQUARE_BRACKETS))) {
				throw new IllegalArgumentException(ERROR_PRODUCT_NOT_SQUARE_BRACKETS);
			}
		}
	}
}
