package com.example.javaspringbootjooq.application;

import com.example.javaspringbootjooq.domain.RandomIdGenerator;
import com.example.javaspringbootjooq.domain.task.Task;
import com.example.javaspringbootjooq.domain.task.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateTaskUseCaseTest {

    @Test
    void testExecute() {
        // RandomNumberGeneratorのstaticメソッドをモック
        MockedStatic<RandomIdGenerator> randomIdGeneratorMockedStatic = mockStatic(RandomIdGenerator.class);

        // generateRandomNumberが100を返すように設定
        when(RandomIdGenerator.generate()).thenReturn(100);

        // タスクを作成
        Task task = new Task("title", "description");

        // TaskRepositoryのモックを作成
        TaskRepository taskRepository = mock(TaskRepository.class);

        // テスト対象のクラスを作成
        CreateTaskUseCase createTaskUseCase = new CreateTaskUseCase(taskRepository);

        CreateTaskCommand createTaskCommand = new CreateTaskCommand("title", "description");

        // メソッドを実行
        createTaskUseCase.execute(createTaskCommand);

        // taskRepositoryのinsertメソッドが呼ばれたことを検証
        verify(taskRepository).insert(task);

        randomIdGeneratorMockedStatic.clearInvocations();
    }
}
