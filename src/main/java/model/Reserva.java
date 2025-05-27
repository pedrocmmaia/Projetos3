package model;

import java.time.LocalDate;

public class Reserva {
    private Integer id;
    private int moradorId;
    private int areaId;
    private LocalDate dataReserva;
    private StatusReserva statusReserva;

    public enum StatusReserva {
        PENDENTE,
        CONFIRMADA,
        CANCELADA;

        public static StatusReserva fromString(String status) {
            status = status.toUpperCase();
            if (status.contains("PENDENTE")) return PENDENTE;
            if (status.contains("CONFIRMADA")) return CONFIRMADA;
            if (status.contains("CANCELADA")) return CANCELADA;
            throw new IllegalArgumentException("Tipo de status inválido: " + status);
        }
    }

    public Reserva(Integer id, int moradorId, int areaId, LocalDate dataReserva, StatusReserva statusReserva) {
        this.id = id;
        this.moradorId = moradorId;
        this.areaId = areaId;
        this.dataReserva = dataReserva;
        this.statusReserva = statusReserva;
    }

    public Reserva(int moradorId, int areaId, LocalDate dataReserva, StatusReserva statusReserva) {
        this.moradorId = moradorId;
        this.areaId = areaId;
        this.dataReserva = dataReserva;
        this.statusReserva = statusReserva;
    }
    public Reserva() {

    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public int getMoradorId() { return moradorId; }

    public void setMoradorId(int moradorId) { this.moradorId = moradorId; }

    public int getAreaId() { return areaId; }

    public void setAreaId(int areaId) { this.areaId = areaId; }

    public LocalDate getDataReserva() { return dataReserva; }

    public void setDataReserva(LocalDate dataReserva) { this.dataReserva = dataReserva; }

    public StatusReserva getStatusReserva() { return statusReserva; }

    public void setStatus(StatusReserva statusReserva) { this.statusReserva = statusReserva; }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", moradorId=" + moradorId +
                ", areaId=" + areaId +
                ", dataReserva=" + dataReserva +
                ", status=" + statusReserva +
                '}';
    }
}
