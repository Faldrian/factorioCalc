package de.bootvogel.factorio.processcalc

class Material(val type: MaterialType, var amount: Float) {
    override fun toString(): String {
        return "Material(type=$type, amount=$amount)"
    }
}