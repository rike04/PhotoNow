package hugo_silva.photonow;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private View viewProgresso;
    private View viewLoginForm;
    private AutoCompleteTextView usernameView;
    private EditText passwordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Instanciação
        usernameView = (AutoCompleteTextView) findViewById(R.id.username);
        passwordView = (EditText) findViewById(R.id.password);
        viewProgresso = findViewById(R.id.login_progress);
        viewLoginForm = findViewById(R.id.login_form);

        Button botaoInicioSessao = (Button) findViewById(R.id.sign_in_button);
        botaoInicioSessao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                iniciarSessao();
            }
        });
    }

    private void iniciarSessao() {

        // Reiniciar os erros das views
        usernameView.setError(null);
        passwordView.setError(null);

        // Guardar os valores da tentativa de inicio de sessão
        String username = usernameView.getText().toString();
        String password = passwordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Verifica se o utilizador introduziu uma palavra-passe e se é válida.
        if (TextUtils.isEmpty(password) || !isPassValida(password)) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }

        // Verifica a validade do nome de utilizador introduzido.
        if (TextUtils.isEmpty(username)) {
            usernameView.setError(getString(R.string.error_field_required));
            focusView = usernameView;
            cancel = true;
        } else if (!isUsernameValido(username)) {
            usernameView.setError(getString(R.string.error_invalid_email));
            focusView = usernameView;
            cancel = true;
        }

        if (cancel) {
            // Ocorreu um erro; Não é tentado o inicio de sessão e
            // é focado no primeiro campo com um erro.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);

            //Confirmar que a aplicação já foi aberta e o login feito
            /*SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
            SharedPreferences.Editor edt = pref.edit();
            edt.putBoolean("activity_executed", true);
            edt.putString("username", username); */

            //Iniciar a atividade main
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
            finish();
        }

    }

    private Boolean isPassValida(String password) {
        return password.length() > 5;
    }

    private Boolean isUsernameValido (String username) {
        return username.length() > 5;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            viewLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
            viewLoginForm.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    viewLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            viewProgresso.setVisibility(show ? View.VISIBLE : View.GONE);
            viewProgresso.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    viewProgresso.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // Caso não esteja disponível o HONEYCOMB, a animação será ignorada
            // e prosseguirá normalmente
            viewProgresso.setVisibility(show ? View.VISIBLE : View.GONE);
            viewLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
            Log.d(getClass().getSimpleName(), "OO");
        }
    }
}
