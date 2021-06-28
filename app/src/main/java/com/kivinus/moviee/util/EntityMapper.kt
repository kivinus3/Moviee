package com.kivinus.moviee.util

interface EntityMapper<Entity, DomainModel> {

    fun mapFromEntity(entity: Entity): DomainModel

    fun mapToEntity(domainModel: DomainModel): Entity

    fun mapListFromEntity(entitiesList: List<Entity>): List<DomainModel>

    fun mapListToEntity(domainModelsList: List<DomainModel>): List<Entity>

}