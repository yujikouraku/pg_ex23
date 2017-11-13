package invoice;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class InvoiceWriter {
	private PrintWriter writer;

	public InvoiceWriter() {
		 this.writer = new PrintWriter(new BufferedWriter(new StringWriter()));
	}

	public InvoiceWriter(Writer writer) {
		this.writer = new PrintWriter(new BufferedWriter(writer));
	}

	public void write(Invoice invoice) {
		String owner_tel_num = invoice.getOwnerTelNumber();
		writer.print("1 " + owner_tel_num + "\n");
		System.out.print("1 " + owner_tel_num + "\n");
		int basic_charge =  invoice.getBasicCharge();
		writer.print("5 " + basic_charge + "\n");
		System.out.print("5 " + basic_charge + "\n");
		int call_charge = invoice.getCallCharge();
		writer.print("7 " + call_charge + "\n");
		System.out.print("7 " + call_charge + "\n");
		writer.print("9 " + "====================" + "\n");
		System.out.print("9 " + "====================" + "\n");

		/*
		output_str = "1 " + owner_tel_num + "\n"
				   + "5 " + basic_charge + "\n"
				   + "7 " + call_charge + "\n"
				   + "9 " + "====================" + "\n";
		*/
	}

	public String toString(){
		return writer.toString();
	}

	public void close() {
		writer.close();
	}

}
