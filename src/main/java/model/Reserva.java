package model;

import java.util.Date;

public class Reserva {
    private Integer id;
    private int moradorId;
    private int areaId;
    private Date dataReserva;
    private StatusReserva status;

    // Construtor completo
    public Reserva(Integer id, int moradorId, int areaId, Date dataReserva, StatusReserva status) {
        this.id = id;
        this.moradorId = moradorId;
        this.areaId = areaId;
        this.dataReserva = dataReserva;
        this.status = status;
    }

    // Construtor para cadastro (sem ID)
    public Reserva(int moradorId, int areaId, Date dataReserva, StatusReserva status) {
        this.moradorId = moradorId;
        this.areaId = areaId;
        this.dataReserva = dataReserva;
        this.status = status;
    }

    // Getters e Setters
    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public int getMoradorId() { return moradorId; }

    public void setMoradorId(int moradorId) { this.moradorId = moradorId; }

    public int getAreaId() { return areaId; }

    public void setAreaId(int areaId) { this.areaId = areaId; }

    public Date getDataReserva() { return dataReserva; }

    public void setDataReserva(Date dataReserva) { this.dataReserva = dataReserva; }

    public StatusReserva getStatus() { return status; }

    public void setStatus(StatusReserva status) { this.status = status; }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", moradorId=" + moradorId +
                ", areaId=" + areaId +
                ", dataReserva=" + dataReserva +
                ", status=" + status +
                '}';
    }
}
