package de.bootvogel.factorio.processcalc


fun main(args: Array<String>) {
    val givenMaterials = listOf(
            MaterialType.IRON_PLATE,
            MaterialType.STEEL_PLATE,
            MaterialType.PLASTIC_BAR,
            MaterialType.COAL,
            MaterialType.PETROLEUM_GAS,
            MaterialType.COPPER_PLATE,
            MaterialType.STONE,
            MaterialType.LUBRICANT,
            MaterialType.SULFURIC_ACID,
            MaterialType.ELECTRONIC_CIRCUIT,
//            MaterialType.ADVANCED_CIRCUIT,
            MaterialType.ENGINE_UNIT,
            MaterialType.PROCESSING_UNIT
    )

    val wantedOutput = listOf(
            Material(MaterialType.PROCESSING_UNIT, 1f)
    )

    val wantedMaterials = wantedOutput.map { it.type }

    // get start factory
    val startFactories = wantedMaterials.map(Factory.Companion::getFactory)

    // get all involved factories
    val factoryList = startFactories
            .map { buildFactoryList(it, givenMaterials) }
            .fold(emptyList<Factory>()) { a, b -> listOf(*a.toTypedArray(), *b.toTypedArray()) }
            .distinct()

    val factoryConfigurationSnapshot = factoryList.map { FactoryConfiguration(it, 0) }

    // set all wanted materials to the necessary number of factories
    factoryConfigurationSnapshot
            .filter { startFactories.contains(it.factory) }
            .forEach { conf -> conf.numberOfFactories = conf.factory.getFactoriesForOutput(wantedOutput.find { conf.factory.output.type == it.type}!!.amount) }


    // calculate amounts of factories to only need givenMaterials and have every other sum = 0
    var effects = cummulativeEffects(factoryConfigurationSnapshot)

    while (!noIntermediateMaterialsNeeded(effects, givenMaterials, wantedMaterials)) {
        // for every material: find the first factory for a material that is still negative and increase the amount of factories
        effects.filter { !givenMaterials.contains(it.type) && it.amount < 0 }
                .map { negativeMaterial -> factoryConfigurationSnapshot.find { it.factory.output.type == negativeMaterial.type } }
                .filter { it != null }
                .forEach { it!!.numberOfFactories++ }

        // recalc effects
        effects = cummulativeEffects(factoryConfigurationSnapshot)
    }

    for (factory in factoryConfigurationSnapshot) {
        println(factory)
    }

    for (effect in effects) {
        println(effect)
    }

}

fun noIntermediateMaterialsNeeded(effects: Collection<Material>, givenMaterials: List<MaterialType>, wantedMaterials: List<MaterialType>): Boolean {
    return effects.map {
        givenMaterials.contains(it.type) || wantedMaterials.contains(it.type) || it.amount >= 0
    }.all { it }
}


fun buildFactoryList(startFactory: Factory, givenMaterials: List<MaterialType>): List<Factory> {
    val dependencies: List<Factory> = startFactory.input
            .filter { !givenMaterials.contains(it.type) }
            .map { buildFactoryList(Factory.getFactory(it.type), givenMaterials) }
            .fold(emptyList()) { a, b -> listOf(*a.toTypedArray(), *b.toTypedArray()) }

    return listOf(startFactory, *dependencies.toTypedArray())
}

fun cummulativeEffects(snapshot: List<FactoryConfiguration>): Collection<Material> {
    var effects = mapOf<MaterialType, Material>()

    snapshot.forEach {
        it.factory.getEffect(it.numberOfFactories).forEach {
            val materialLookup = effects[it.type]
            if (materialLookup == null)
                effects += Pair(it.type, it)
            else
                materialLookup.amount += it.amount
        }
    }

    return effects.values
}

