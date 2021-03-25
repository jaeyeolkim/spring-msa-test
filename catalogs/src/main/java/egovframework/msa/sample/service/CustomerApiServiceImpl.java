package egovframework.msa.sample.service;

import org.springframework.stereotype.Service;

@Service
public class CustomerApiServiceImpl implements CustomerApiService {

    @Override
    public String getCustomer(String customerId) {
        return customerId;
    }
}
