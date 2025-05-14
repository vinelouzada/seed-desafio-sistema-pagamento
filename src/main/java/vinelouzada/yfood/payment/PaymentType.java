package vinelouzada.yfood.payment;

public enum PaymentType {
    MASTER_CARD("Pagamento com MASTER", ModePay.ONLINE),
    VISA_CARD("Pagamento com VISA", ModePay.ONLINE),
    ELO_CARD("Pagamento com ELO", ModePay.ONLINE),
    HIPER_CARD("Pagamento com HIPER", ModePay.ONLINE),
    CASH("Pagamento por dinheiro", ModePay.OFFLINE),
    CHECK("Pagamento por cheque", ModePay.OFFLINE),
    POS_DEVICE("Pagamento por máquina", ModePay.OFFLINE),;

    private final String description;
    private final ModePay modePay;

    PaymentType(String description, ModePay modePay) {
        this.description = description;
        this.modePay = modePay;
    }

    public String getDescription() {
        return description;
    }

    public String getModePay() {
        return modePay.toString();
    }

    private enum ModePay{
        ONLINE,
        OFFLINE,
    }
}
