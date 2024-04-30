package fatec.sp.gov.br.smartleaf.domain.repository;

import fatec.sp.gov.br.smartleaf.domain.model.FotoSolarPanel;
import fatec.sp.gov.br.smartleaf.domain.model.SolarPanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SolarPanelRepository extends JpaRepository<SolarPanel, Long>, SolarPanelRepositoryQueries {
    @Query("from FotoSolarPanel where solarPanel.id = :id")
    Optional<FotoSolarPanel> findFotoById(Long id);

}
