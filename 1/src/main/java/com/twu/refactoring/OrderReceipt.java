package com.twu.refactoring;

public class OrderReceipt {
    private final Order order;

    public OrderReceipt(Order order) {
        this.order = order;
	}

	public String printReceipt() {
		StringBuilder output = new StringBuilder();
		String header = "======Printing Orders======\n";
		output.append(header);
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());

		printLineItems(output);
		return output.toString();
	}

	private void printLineItems(StringBuilder output) {
		double totalSalesTax = 0d;
		double totalAmount = 0d;
		for (LineItem lineItem : order.getLineItems()) {
			appendStringWithHT(output, lineItem.getDescription());
			appendStringWithHT(output, String.valueOf(lineItem.getPrice()));
			appendStringWithHT(output, String.valueOf(lineItem.getQuantity()));
			appendStringWithHT(output, String.valueOf(lineItem.totalAmount()));

			double taxRate = 0.10d;
            double salesTax = lineItem.totalAmount() * taxRate;
            totalSalesTax += salesTax;
            totalAmount += lineItem.totalAmount() + salesTax;
		}

		appendStringWithHT(output, "Sales Tax");
		output.append(totalSalesTax);

		appendStringWithHT(output, "Total Amount");
		output.append(totalAmount);
	}

	private void appendStringWithHT(StringBuilder output, String string) {
		output.append(string);
		output.append('\t');
	}
}