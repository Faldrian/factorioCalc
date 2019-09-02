package de.bootvogel.factorio.processcalc


fun main() {
    val givenMaterials = listOf(
            MaterialType.IRON_ORE,
            MaterialType.COPPER_ORE,
            MaterialType.COAL
    )

    val wantedOutput = listOf(
            Material(MaterialType.MILITARY_SCIENCE_PACK, 3f)
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
    val effects = mutableMapOf<MaterialType, Material>()

    snapshot.forEach { factoryConfiguration ->
        factoryConfiguration.factory.getEffect(factoryConfiguration.numberOfFactories).forEach { material ->
            val materialLookup = effects[material.type]
            if (materialLookup == null)
                effects += Pair(material.type, material)
            else
                materialLookup.amount += material.amount
        }
    }

    return effects.values
}

