package repositories.bd;

import java.util.Arrays;
import java.util.List;

import repositories.IAnneeRepository;

public class AnneeRepository implements IAnneeRepository {

    @Override
    public List<String> findAll() {
        return Arrays.asList("2021-2022","2023-2024");
    }
    
}
