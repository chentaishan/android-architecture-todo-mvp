package com.mvp.demo.mvp;

/**
 * Use cases are the entry points to the domain layer.
 *
 *是指结合数据流和实体中的用例，也称为Interactor（交互器）
 * @param <Q> the request type
 * @param <P> the response type
 */
public abstract class UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValue> {

    private Q mRequestValues;

    private UseCaseCallback<P> mUseCaseCallback;

    public void setRequestValues(Q requestValues) {
        mRequestValues = requestValues;
    }

    public Q getRequestValues() {
        return mRequestValues;
    }

    public UseCaseCallback<P> getUseCaseCallback() {
        return mUseCaseCallback;
    }

    public void setUseCaseCallback(UseCaseCallback<P> useCaseCallback) {
        mUseCaseCallback = useCaseCallback;
    }

    void run() {
       executeUseCase(mRequestValues);
    }

    protected abstract void executeUseCase(Q requestValues);

    public interface RequestValues {
    	
    }

    public interface ResponseValue {
    	
    }
    
    public interface UseCaseCallback<R> {
        void onSuccess(R response);
        void onError();
    }
}
