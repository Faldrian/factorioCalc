package de.bootvogel.factorio.processcalc

class Factory(val output: Material, val input: List<Material>, val timePerProcess: Float, val factorySpeed: Float = factorySpeedYellow) {
    companion object {
        val factorySpeedGrey: Float = 0.5f
        val factorySpeedBlue: Float = 0.75f
        val factorySpeedYellow: Float = 1.25f

        val factorySpeedElecticSmelter: Float = 2f
        val factorySpeedChemistry: Float = 1.25f


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
                Factory(Material(MaterialType.MILITARY_SCIENCE_PACK, 2f),
                        listOf(
                                Material(MaterialType.GRENADE, 1f),
                                Material(MaterialType.GUN_TURRET, 1f),
                                Material(MaterialType.PIERCING_ROUNDS_MAGAZINE, 1f)
                        ),
                        10f),
                Factory(Material(MaterialType.SCIENCE_PACK_3, 1f),
                        listOf(
                                Material(MaterialType.ADVANCED_CIRCUIT, 1f),
                                Material(MaterialType.ENGINE_UNIT, 1f),
                                Material(MaterialType.MINING_DRILL, 1f)
                        ),
                        12f),


                // PRODUCTION THINGS
                Factory(Material(MaterialType.ELECTRIC_FURNACE, 1f),
                        listOf(
                                Material(MaterialType.STEEL_PLATE, 10f),
                                Material(MaterialType.ADVANCED_CIRCUIT, 5f),
                                Material(MaterialType.STONE_BRICK, 10f)
                        ),
                        5f),
                Factory(Material(MaterialType.MINING_DRILL, 1f),
                        listOf(
                                Material(MaterialType.IRON_PLATE, 10f),
                                Material(MaterialType.IRON_GEAR_WHEEL, 5f),
                                Material(MaterialType.ELECTRONIC_CIRCUIT, 3f)
                        ),
                        2f),


                // MILITARY THINGS
                Factory(Material(MaterialType.GRENADE, 1f),
                        listOf(
                                Material(MaterialType.COAL, 10f),
                                Material(MaterialType.IRON_PLATE, 5f)
                        ),
                        8f),
                Factory(Material(MaterialType.GUN_TURRET, 1f),
                        listOf(
                                Material(MaterialType.COPPER_PLATE, 10f),
                                Material(MaterialType.IRON_GEAR_WHEEL, 10f),
                                Material(MaterialType.IRON_PLATE, 20f)
                        ),
                        8f),
                Factory(Material(MaterialType.PIERCING_ROUNDS_MAGAZINE, 1f),
                        listOf(
                                Material(MaterialType.COPPER_PLATE, 5f),
                                Material(MaterialType.FIREARM_MAGAZINE, 1f),
                                Material(MaterialType.STEEL_PLATE, 1f)
                        ),
                        3f),
                Factory(Material(MaterialType.FIREARM_MAGAZINE, 1f),
                        listOf(
                                Material(MaterialType.IRON_PLATE, 4f)
                        ),
                        1f),


                // CHEMISTY
                Factory(Material(MaterialType.PLASTIC_BAR, 2f),
                        listOf(
                                Material(MaterialType.COAL, 1f),
                                Material(MaterialType.PETROLEUM_GAS, 20f)
                        ),
                        1f,
                        factorySpeedChemistry),
                Factory(Material(MaterialType.BATTERY, 1f),
                        listOf(
                                Material(MaterialType.IRON_PLATE, 1f),
                                Material(MaterialType.COPPER_PLATE, 1f),
                                Material(MaterialType.SULFURIC_ACID, 20f)
                        ),
                        5f,
                        factorySpeedChemistry),


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
                        factorySpeedElecticSmelter),


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
                        factorySpeedElecticSmelter),
                Factory(Material(MaterialType.IRON_PLATE, 1f),
                        listOf(
                                Material(MaterialType.IRON_ORE, 1f)
                        ),
                        3.5f,
                        factorySpeedElecticSmelter),

                // STONES

                Factory(Material(MaterialType.STONE_BRICK, 1f),
                        listOf(
                                Material(MaterialType.STONE, 2f)
                        ),
                        3.5f,
                        factorySpeedElecticSmelter),

                // SATELLITE STUFF
                Factory(Material(MaterialType.SATELLITE, 1f),
                        listOf(
                                Material(MaterialType.PROCESSING_UNIT, 100f),
                                Material(MaterialType.LOW_DENSITY_STRUCTURE, 100f),
                                Material(MaterialType.ROCKET_FUEL, 50f),
                                Material(MaterialType.SOLAR_PANEL, 100f),
                                Material(MaterialType.ACCUMULATOR, 100f),
                                Material(MaterialType.RADAR, 5f)
                        ),
                        5f),

                Factory(Material(MaterialType.LOW_DENSITY_STRUCTURE, 1f),
                        listOf(
                                Material(MaterialType.COPPER_PLATE, 5f),
                                Material(MaterialType.STEEL_PLATE, 10f),
                                Material(MaterialType.PLASTIC_BAR, 5f)
                        ),
                        30f),

                Factory(Material(MaterialType.ROCKET_FUEL, 1f),
                        listOf(
                                Material(MaterialType.SOLID_FUEL, 10f)
                        ),
                        30f),

                Factory(Material(MaterialType.SOLAR_PANEL, 1f),
                        listOf(
                                Material(MaterialType.COPPER_PLATE, 5f),
                                Material(MaterialType.STEEL_PLATE, 5f),
                                Material(MaterialType.ELECTRONIC_CIRCUIT, 15f)
                        ),
                        10f),

                Factory(Material(MaterialType.ACCUMULATOR, 1f),
                        listOf(
                                Material(MaterialType.IRON_PLATE, 2f),
                                Material(MaterialType.BATTERY, 5f)
                        ),
                        10f),

                Factory(Material(MaterialType.RADAR, 1f),
                        listOf(
                                Material(MaterialType.IRON_PLATE, 10f),
                                Material(MaterialType.IRON_GEAR_WHEEL, 5f),
                                Material(MaterialType.ELECTRONIC_CIRCUIT, 5f)
                        ),
                        0.5f),

                Factory(Material(MaterialType.SOLID_FUEL, 1f),
                        listOf(
                                Material(MaterialType.PETROLEUM_GAS, 20f)
                        ),
                        3f,
                        factorySpeedChemistry),

                Factory(Material(MaterialType.ROCKET_CONTROL_UNIT, 1f),
                        listOf(
                                Material(MaterialType.PROCESSING_UNIT, 1f),
                                Material(MaterialType.SPEED_MODULE, 1f)
                        ),
                        30f),

                // ARTILLERY_SHELL
                Factory(Material(MaterialType.ARTILLERY_SHELL, 1f),
                        listOf(
                                Material(MaterialType.EXPLOSIVES, 8f),
                                Material(MaterialType.EXPLOSIVE_CANNON_SHELL, 4f),
                                Material(MaterialType.RADAR, 1f)
                        ),
                        15f),

                Factory(Material(MaterialType.EXPLOSIVE_CANNON_SHELL, 1f),
                        listOf(
                                Material(MaterialType.STEEL_PLATE, 2f),
                                Material(MaterialType.PLASTIC_BAR, 2f),
                                Material(MaterialType.EXPLOSIVES, 2f)
                        ),
                        8f),

                Factory(Material(MaterialType.EXPLOSIVES, 2f),
                        listOf(
                                Material(MaterialType.COAL, 1f),
                                Material(MaterialType.SULFUR, 1f),
                                Material(MaterialType.WATER, 10f)
                        ),
                        5f),

                Factory(Material(MaterialType.SULFUR, 2f),
                        listOf(
                                Material(MaterialType.WATER, 30f),
                                Material(MaterialType.PETROLEUM_GAS, 30f)
                        ),
                        1f)
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
        return Math.ceil((1 / (output.amount * factorySpeed * (1 / timePerProcess) * (1 / amount))).toDouble()).toInt()
    }


}
