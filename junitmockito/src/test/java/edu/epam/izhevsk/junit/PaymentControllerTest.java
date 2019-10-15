package edu.epam.izhevsk.junit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalMatchers.geq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentControllerTest {
    private static AccountService accountService;
    private static DepositService depositService;
    private static PaymentController paymentController;

    @BeforeAll
    public static void initServices() throws InsufficientFundsException {
        accountService = Mockito.mock(AccountService.class);
        depositService = Mockito.mock(DepositService.class);
        paymentController = new PaymentController(accountService, depositService);

        when(accountService.isUserAuthenticated(100L)).thenReturn(true);
        when(depositService.deposit(geq(100L), anyLong()))
                .thenThrow(InsufficientFundsException.class);
    }

    @Test
    public void testSuccessfulDeposit() throws InsufficientFundsException {
        paymentController.deposit(50L, 100L);
        verify(accountService).isUserAuthenticated(100L);
    }

    @Test
    public void testUnauthenticatedUserDoDeposit() {
        assertThrows(SecurityException.class, () -> paymentController.deposit(10L, 30L));
    }

    @Test
    public void testLargeAmountDeposit() throws InsufficientFundsException {
        assertThrows(InsufficientFundsException.class, () -> paymentController.deposit(110L, 100L));
        verify(depositService).deposit(110L, 100L);
    }
}
