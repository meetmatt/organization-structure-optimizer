package lu.golikov.oso.application;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ServiceTest {
    @Test
    public void testAnalyzeReturnsOutput() {
        OutputFactory mockOutputFactory = mock(OutputFactory.class);
        Input input = new Input("test.csv");

        Service service = new Service(mockOutputFactory);
        service.analyze(input);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mockOutputFactory).build(captor.capture());

        assertEquals("Analyzed file test.csv", captor.getValue());
    }
}
