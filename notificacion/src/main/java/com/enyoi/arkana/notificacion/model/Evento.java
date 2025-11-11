package com.enyoi.arkana.notificacion.model;

public enum Evento {
    CONFIRMAR, DESPACHAR, ENTREGADO, CANCELADO, CARRITO_ABANDONADO;

    public static Evento fromEstadoCode(int code) {
        return switch (code) {
            case 1 -> CONFIRMAR;
            case 2 -> DESPACHAR;
            case 4 -> ENTREGADO;
            case 3 -> CANCELADO;
            default -> throw new IllegalArgumentException("Estado no valido: " + code);
        };
    }
}
