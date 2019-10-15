package edu.epam.izhevsk.junit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalMatchers.geq;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class PaymentControllerTest {
    @Mock
    private static AccountService accountService;
    @Mock
    private static DepositService depositService;
    @InjectMocks
    private PaymentController paymentController;

    @BeforeAll
    static void initServices() throws InsufficientFundsException {
        accountService = Mockito.mock(AccountService.class);
        depositService = Mockito.mock(DepositService.class);

        when(accountService.isUserAuthenticated(100L)).thenReturn(true);
        when(depositService.deposit(geq(100L), anyLong()))
                .thenThrow(InsufficientFundsException.class);
    }

    @Test
    public void testSuccessfulDeposit() throws InsufficientFundsException {
        verify(paymentController).deposit(50L, 100L);
    }

    @Test
    public void testUnauthenticatedUserDoDeposit() {
        assertThrows(SecurityException.class, () -> paymentController.deposit(10L, 30L));
    }

    @Test
    public void testLargeAmountDeposit() {
        assertThrows(InsufficientFundsException.class, () -> paymentController.deposit(100L, 100L));
    }
}
