package egovframework.msa.sample.service;

public interface CustomerApiService {
    String getCustomer(String customerId);
    String getCustomerApi(String customerId);
    String getCustomerApiException(String customerId);
}
