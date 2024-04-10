import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class YourServiceTest {

    @Mock
    private CircuitBreakerFactory cBFactory;

    private YourService yourService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        yourService = new YourService(cBFactory);
    }

    @Test
    public void testGetAccountDetails_SuccessfulExecution() {
        // Mock CircuitBreaker
        CircuitBreaker cB = mock(CircuitBreaker.class);
        when(cBFactory.create("getAccountDetails")).thenReturn(cB);

        // Mocking downstream method and headers
        String accountNumber = "123456";
        Headers headers = new Headers();
        UserAccounts expectedAccounts = new UserAccounts(); // Mocked expected accounts

        // Mocking successful execution
        when(cB.run(any(), any())).thenAnswer(invocation -> {
            // Execute the actual method
            return expectedAccounts;
        });

        // Call the method
        UserAccounts result = yourService.getAccountDetails(accountNumber, headers);

        // Assert the result
        assertEquals(expectedAccounts, result);
        verify(cBFactory).create("getAccountDetails");
    }

    @Test
    public void testGetAccountDetails_FallbackExecution() {
        // Mock CircuitBreaker
        CircuitBreaker cB = mock(CircuitBreaker.class);
        when(cBFactory.create("getAccountDetails")).thenReturn(cB);

        // Mocking downstream method and headers
        String accountNumber = "123456";
        Headers headers = new Headers();
        UserAccounts expectedFallbackAccounts = new UserAccounts(); // Mocked expected fallback accounts

        // Mocking fallback execution
        when(cB.run(any(), any())).thenAnswer(invocation -> {
            // Simulate a failure and invoke fallback
            throw new RuntimeException("Simulated failure");
        });

        // Mock the fallback method
        doReturn(expectedFallbackAccounts).when(yourService).getAccountDetailsFallBack(accountNumber, headers);

        // Call the method
        UserAccounts result = yourService.getAccountDetails(accountNumber, headers);

        // Assert the result
        assertEquals(expectedFallbackAccounts, result);
        verify(cBFactory).create("getAccountDetails");
        verify(yourService).getAccountDetailsFallBack(accountNumber, headers);
    }

    // You can write more tests to cover edge cases and exceptions
}
