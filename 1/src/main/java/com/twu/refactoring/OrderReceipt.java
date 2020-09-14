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
			outputAppendLineItemMessageAndHT(output, lineItem.getDescription());
			outputAppendLineItemMessageAndHT(output, String.valueOf(lineItem.getPrice()));
			outputAppendLineItemMessageAndHT(output, String.valueOf(lineItem.getQuantity()));
			output.append(lineItem.totalAmount()).append('\n');

			double taxRate = 0.10d;
            double salesTax = lineItem.totalAmount() * taxRate;
            totalSalesTax += salesTax;
            totalAmount += lineItem.totalAmount() + salesTax;
		}

		outputAppendLineItemMessageAndHT(output, "Sales Tax");
		output.append(totalSalesTax);
		outputAppendLineItemMessageAndHT(output, "Total Amount");
		output.append(totalAmount);
	}

	private void outputAppendLineItemMessageAndHT(StringBuilder output, String lineItemMessage) {
		output.append(lineItemMessage).append('\t');
	}
}