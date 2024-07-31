package mz.sga.ujc.demo.service.paramentrization;

import mz.sga.ujc.demo.model.parametrization.Provincia;
import mz.sga.ujc.demo.repository.parametrization.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService {

    private final ProvinciaRepository repository;
    @Autowired
    public ProvinceService(ProvinciaRepository repository) {
        this.repository = repository;
    }

    public List<Provincia> provinceList() {
        return repository.findAll();
    }
}
