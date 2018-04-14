package de.bootvogel.factorio.processcalc

class Factory(val output: Material, val input: List<Material>, val timePerProcess: Float, val factorySpeed: Float = 0.75f) {
    companion object {
        val factoryList: List<Factory> = listOf(
                // SCIENCE PACKS
                Factory(Material(MaterialType.PRODUCTION_SCIENCE_PACK, 2f),
                        listOf(
                                Material(MaterialType.ELECTRIC_FURNACE, 1f),
                                Material(MaterialType.ELECTRIC_ENGINE_UNIT, 1f)
                        ),
                        14f),
                Factory(Material(MaterialType.HIGH_TECH_SCIENCE_PACK, 2f),
                        listOf(
                                Material(MaterialType.BATTERY, 1f),
                                Material(MaterialType.COPPER_CABLE, 30f),
                                Material(MaterialType.PROCESSING_UNIT, 3f),
                                Material(MaterialType.SPEED_MODULE, 1f)
                        ),
                        14f),


                // PRODUCTION THINGS
                Factory(Material(MaterialType.ELECTRIC_FURNACE, 1f),
                        listOf(
                                Material(MaterialType.STEEL_PLATE, 10f),
                                Material(MaterialType.ADVANCED_CIRCUIT, 5f),
                                Material(MaterialType.STONE, 10f)
                        ),
                        5f),


                // CHEMISTY
                Factory(Material(MaterialType.PLASTIC_BAR, 2f),
                        listOf(
                                Material(MaterialType.COAL, 1f),
                                Material(MaterialType.PETROLEUM_GAS, 20f)
                        ),
                        1f,
                        1.25f),
                Factory(Material(MaterialType.BATTERY, 1f),
                        listOf(
                                Material(MaterialType.IRON_PLATE, 1f),
                                Material(MaterialType.COPPER_PLATE, 1f),
                                Material(MaterialType.SULFURIC_ACID, 20f)
                        ),
                        5f,
                        1.25f),


                // EFFICIENCY MODULES
                Factory(Material(MaterialType.SPEED_MODULE, 1f),
                        listOf(
                                Material(MaterialType.ELECTRONIC_CIRCUIT, 5f),
                                Material(MaterialType.ADVANCED_CIRCUIT, 5f)
                        ),
                        15f),


                // CHIPS
                Factory(Material(MaterialType.PROCESSING_UNIT, 1f),
                        listOf(
                                Material(MaterialType.ELECTRONIC_CIRCUIT, 20f),
                                Material(MaterialType.ADVANCED_CIRCUIT, 2f),
                                Material(MaterialType.SULFURIC_ACID, 5f)
                        ),
                        10f),
                Factory(Material(MaterialType.ADVANCED_CIRCUIT, 1f),
                        listOf(
                                Material(MaterialType.PLASTIC_BAR, 2f),
                                Material(MaterialType.COPPER_CABLE, 4f),
                                Material(MaterialType.ELECTRONIC_CIRCUIT, 2f)
                        ),
                        6f),
                Factory(Material(MaterialType.ELECTRONIC_CIRCUIT, 1f),
                        listOf(
                                Material(MaterialType.COPPER_CABLE, 3f),
                                Material(MaterialType.IRON_PLATE, 1f)
                        ),
                        0.5f),


                // ENGINES
                Factory(Material(MaterialType.ELECTRIC_ENGINE_UNIT, 1f),
                        listOf(
                                Material(MaterialType.ELECTRONIC_CIRCUIT, 2f),
                                Material(MaterialType.ENGINE_UNIT, 1f),
                                Material(MaterialType.LUBRICANT, 15f)
                        ),
                        10f),
                Factory(Material(MaterialType.ENGINE_UNIT, 1f),
                        listOf(
                                Material(MaterialType.IRON_GEAR_WHEEL, 1f),
                                Material(MaterialType.PIPE, 2f),
                                Material(MaterialType.STEEL_PLATE, 1f)
                        ),
                        10f),


                // COPPER STUFF
                Factory(Material(MaterialType.COPPER_CABLE, 2f),
                        listOf(
                                Material(MaterialType.COPPER_PLATE, 1f)
                        ),
                        0.5f),
                Factory(Material(MaterialType.COPPER_PLATE, 1f),
                        listOf(
                                Material(MaterialType.COPPER_ORE, 1f)
                        ),
                        3.5f,
                        2f),


                // IRON STUFF
                Factory(Material(MaterialType.PIPE, 1f),
                        listOf(
                                Material(MaterialType.IRON_PLATE, 1f)
                        ),
                        0.5f),
                Factory(Material(MaterialType.IRON_GEAR_WHEEL, 1f),
                        listOf(
                                Material(MaterialType.IRON_PLATE, 2f)
                        ),
                        0.5f),
                Factory(Material(MaterialType.STEEL_PLATE, 1f),
                        listOf(
                                Material(MaterialType.IRON_PLATE, 5f)
                        ),
                        17.5f,
                        2f),
                Factory(Material(MaterialType.IRON_PLATE, 1f),
                        listOf(
                                Material(MaterialType.IRON_ORE, 1f)
                        ),
                        3.5f,
                        2f)
        )

        fun getFactory(type: MaterialType): Factory {
            if (factoryList.none { it.output.type == type })
                throw Exception("No Factory for $type!")

            return factoryList.first { it.output.type == type }
        }
    }

    fun getEffect(numberOfFactories: Int): List<Material> {
        return listOf(
                *input.map { Material(it.type, it.amount * factorySpeed * (1 / timePerProcess) * numberOfFactories * -1) }.toTypedArray(),
                Material(output.type, output.amount * factorySpeed * (1 / timePerProcess) * numberOfFactories)
        )
    }

    override fun toString(): String {
        return "Factory for " + output.type
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Factory

        if (output != other.output) return false

        return true
    }

    override fun hashCode(): Int {
        return output.hashCode()
    }

    fun getFactoriesForOutput(amount: Float): Int {
        return Math.ceil((1 / (output.amount * factorySpeed * (1 / timePerProcess) * (1/amount))).toDouble()).toInt()
    }


}
