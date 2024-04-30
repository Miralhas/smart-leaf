package fatec.sp.gov.br.smartleaf.infrastructure.repository;

import fatec.sp.gov.br.smartleaf.domain.model.FotoSolarPanel;
import fatec.sp.gov.br.smartleaf.domain.repository.SolarPanelRepository;
import fatec.sp.gov.br.smartleaf.domain.repository.SolarPanelRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SolarPanelRepositoryImpl implements SolarPanelRepositoryQueries {

    @PersistenceContext
    private final EntityManager entityManager;


    public SolarPanelRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public FotoSolarPanel saveImage(FotoSolarPanel foto) {
        // É atribuida a foto recebida pelo argumento.
        return entityManager.merge(foto);
    }

    @Transactional
    @Override
    public void deleteImage(FotoSolarPanel fotoSolarPanel) {
        entityManager.remove(fotoSolarPanel);
    }
}
