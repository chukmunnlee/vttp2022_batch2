package vttp2022.paf.assessment.eshop.models;

public class OrderStatusCount {

	private String name;
	private Integer dispatched = 0;
	private Integer pending = 0;

	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }

	public void setDispatched(int dispatched) { this.dispatched = dispatched; }
	public int getDispatched() { return this.dispatched; }

	public void setPending(int pending) { this.pending = pending; }
	public int getPending() { return this.pending; }

	@Override
	public String toString() {
		return "OrderStatusCount{name=%s, dispatched=%d, pending=%d}"
				.formatted(name, dispatched, pending);
	}
}
