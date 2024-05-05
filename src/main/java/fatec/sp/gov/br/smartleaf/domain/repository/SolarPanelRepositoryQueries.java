package fatec.sp.gov.br.smartleaf.domain.repository;

import fatec.sp.gov.br.smartleaf.domain.model.FotoSolarPanel;

public interface SolarPanelRepositoryQueries {
    FotoSolarPanel saveImage(FotoSolarPanel foto);
    void deleteImage(FotoSolarPanel fotoSolarPanel);
}
