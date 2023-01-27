export interface LineItem {
	item: string
	quantity: number
	unitPrice: number
}
export interface Order {
	name: string
	address: string
	email: string
	phone: string
	express: boolean
	delivery: string
	lineItems: LineItem[]
}
