package guru.qa.niffler.jupiter.extension;

import guru.qa.niffler.api.SpendApi;
import guru.qa.niffler.model.SpendJson;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

public class SpendHttpExtension extends AbstractSpendExtension{

    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(BODY))
            .build();

    private final Retrofit retrofit = new Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://127.0.0.1:8093/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();
    @Override
    protected SpendJson createSpend(SpendJson spend) {
        SpendApi spendApi = retrofit.create(SpendApi.class);
        SpendJson spendJson = new SpendJson(
                null,
                new Date(),
                spend.category(),
                spend.currency(),
                spend.amount(),
                spend.description(),
                spend.username());
        try {
            spend = Objects.requireNonNull(spendApi.createSpend(spendJson).execute().body());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return spend;
    }

    protected SpendJson editSpend(SpendJson spend) {
        SpendApi spendApi = retrofit.create(SpendApi.class);
        SpendJson spendJson = new SpendJson(
                spend.id(),
                spend.spendDate(),
                spend.category(),
                spend.currency(),
                spend.amount(),
                spend.description(),
                spend.username());
        try {
            spend = Objects.requireNonNull(spendApi.editSpend(spendJson).execute().body());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return spend;
    }

    @Override
    protected void removeSpend(SpendJson spendJson) {
        SpendApi spendApi = retrofit.create(SpendApi.class);
        spendApi.removeSpend(spendJson.username(), new ArrayList<>(List.of(spendJson.id().toString())));

        }
}
