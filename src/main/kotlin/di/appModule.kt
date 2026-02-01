package ies.sequeros.dam.ad.orm.di

import ies.sequeros.dam.ad.orm.application.categorias.AddCategoriaUseCase
import ies.sequeros.dam.ad.orm.application.categorias.DeleteCategoriaUseCase
import ies.sequeros.dam.ad.orm.application.categorias.GetAllCategoriaUseCase
import ies.sequeros.dam.ad.orm.application.categorias.GetCategoriaUseCase
import ies.sequeros.dam.ad.orm.application.categorias.UpdateCategoriaUseCase
import ies.sequeros.dam.ad.orm.application.productos.AddProductoUseCase
import ies.sequeros.dam.ad.orm.application.productos.DeleteProductoUseCase
import ies.sequeros.dam.ad.orm.application.productos.GetAllProductosUseCase
import ies.sequeros.dam.ad.orm.application.productos.GetProductoUseCase
import ies.sequeros.dam.ad.orm.application.productos.GetProductoWithCategoriaUseCase
import ies.sequeros.dam.ad.orm.application.productos.UpdateProductoUseCase
import ies.sequeros.dam.ad.orm.domain.ICategoriaRepository
import ies.sequeros.dam.ad.orm.domain.IProductoRepository
import ies.sequeros.dam.ad.orm.infraestructure.repositories.JPACategoriaRepository
import ies.sequeros.dam.ad.orm.infraestructure.repositories.JPAProductoRepository
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import org.koin.dsl.module
import org.koin.dsl.onClose

val appModulo = module {

    single<EntityManagerFactory> {
        Persistence.createEntityManagerFactory("UnidadPersistencia")
    }.onClose {
        //se cierra la factoria
        it?.close()
    }
    factory {
        get<EntityManagerFactory>().createEntityManager()
    }
    // Repositorios
    //infiere que necesita un entity manager y se lo da automaticamente
    single<ICategoriaRepository> { JPACategoriaRepository(get()) }
    //infiere que los casos de uso necesitan un ICategoriaRepository y lo inyecta
    factory { GetCategoriaUseCase(get()) } //, get()) }
    factory { AddCategoriaUseCase(get()) }
    factory { UpdateCategoriaUseCase(get()) }
    factory { DeleteCategoriaUseCase(get()) }
    factory { GetAllCategoriaUseCase(get()) }

    single<IProductoRepository> { JPAProductoRepository(get ()) }
    factory { AddProductoUseCase(get()) }
    factory { DeleteProductoUseCase(get()) }
    factory { GetAllProductosUseCase(get()) }
    factory { GetProductoUseCase(get()) }
    factory { GetProductoWithCategoriaUseCase(get()) }
    factory { UpdateProductoUseCase(get()) }

}