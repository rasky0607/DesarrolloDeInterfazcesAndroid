package com.example.inventoryfragment.iu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.inventoryfragment.R;
import com.example.inventoryfragment.utils.CommonUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SingUpActivity extends AppCompatActivity {

    private Button btSingUp;
    /*private TextInputLayout  pero al obtener el texto del EditText que esta dentro de este elemento , debemos hacer primero getEditText() y despues el getText()
    * con TextInputEditText en teoria no hace falta getEditext*/

    private TextInputEditText tieNameUser;
    private TextInputEditText tiepassword;
    private TextInputEditText tieemail;


    private TextInputLayout tiNameUser;
    private TextInputLayout tipassword;
    private TextInputLayout tiemail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        btSingUp=findViewById(R.id.btSingUp);

        //Recogemos campos:
        tiNameUser=findViewById(R.id.tiNameUser);
        tipassword=findViewById(R.id.tipassword);
        tiemail=findViewById(R.id.tiemail);

        //Creo un objeto de la clase SIngUpWatcher
        /*Para lanzar  un mensjae de error en cuanto el patron no se cumple y que este desaparezca cuando comenzamos de nuevo a escribir:
         Creamos un manejador al que le pasamos la clase SingUpWatcher, la cual implementa el interfaz de TextWatcher */

        tieNameUser=findViewById(R.id.tieNameUser);
        tiepassword=findViewById(R.id.tiepassword);
        tieemail=findViewById(R.id.tieemail);

        //tieNameUser.addTextChangedListener(SingUpWatcher(tieNameUser));
        tieNameUser.addTextChangedListener(new SingUpWatcher(tieNameUser));
        tiepassword.addTextChangedListener(new SingUpWatcher(tiepassword));
        tieemail.addTextChangedListener(new SingUpWatcher(tieemail));



        btSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validar();
            }
        });
    }

    /*Metodo que comprueba la validez de estos dos campos Usiario y Password*/
    private void Validar() {

       if(ValidateUser(tiNameUser.getEditText().getText().toString())&& ValidatePassword(tipassword.getEditText().getText().toString())&& ValidateEmail(tiemail.getEditText().getText().toString()))
       {
           //1. Se guarda el usuario en la BD
           //2 Envio correo al usuario (Firebase lo hace automaticamente)
           //3. Se pasa a la ventana Login

           /*Por ahora solo volvemos al Login(cogemos la Activity Login que ya se creo antes
            al pasar del Splash al Login(volviendo a la ultima activity que esta en la pila)
            , la cual fue la que llamoa esta , osea el Login*/
           //intent.setFlag
          /* Intent intent =new Intent(SingUpActivity.this,LoginActivity.class);//falta poner el flag para coger la instancia anterior de la activity, es decir cogerla de la pila
           //Coge la ultima activity de la pila , si la activity a la que nos dirigimos es una que esta la ultima en la pila
           intent.setFlags(intent.FLAG_ACTIVITY_SINGLE_TOP);
           startActivity((intent));*/

           /*Usando android:launchMode="singleTask" en la activity de SingUpActivity
            en el manifieste y android:launchMode="singleTop" enla activity login, de esta forma volvemos a la misma instancia de login que hay en la pila
            Ya que si creamos un new Intent realmente estamos creando una nueva instancia de ActivityLogin*/

           //Si la actividad es la ultima en la pila  no es necesario crear un nuevo Intent para volver a la instancia anterior de Login:
            /*a que esta actividad no va volver a ser usada y los datos son insertados en la BD
           (esta activity no hablara con ninguna otra activity), la destruimos*/
           finish();
       }

    }

    /*Valida el Usuario introducido en la activity
    * @param*/
    private boolean ValidateUser(String nameUser) {
        //1. No puede ser vacio.

        //forma de Lourdes "Si el texto de la variable nameUser es vacio"
        //return  !TextUtils.isEmpty(nameUser);
       // //Mi forma -> nameUser.isEmpty();

        if(TextUtils.isEmpty(nameUser)) {
            //Mostramos el error
            tiNameUser.setError(getString(R.string.errUserEmty));//Los errores van guardados en otro fichero String
            displaySoftKeyboard(tieNameUser);//Devolvemos el foco
            Log.e("Error usuario","Usuario VACIO");
            return false;
        }else
        {
            tiNameUser.setError(null);//para eliminar el error mostrado tras comenza de nuevo
            Log.e("BIEN usuario","USuario NO VACIO");
            return true;
        }

    }


    /*Valida el Password introducido en la activity*/
    private boolean ValidatePassword(String password) {
        //1. No pueden ser vacias ninguna de las dos
        //2.Tamaño minimo 8 y maximo 15(controlado ya por el interfaz),una mayuscula y un numero minimo
        //3. Comprueba que las contraseñas son iguales (password==PasswordConfirm)

        if(!CommonUtils.CheckPatternPassword(password))
        {
           /* if(!CommonUtils.CheckPatternEmail(password))
                Log.e("Error password","PASSWORD NO CUMPLE PATRON");*/

            //Muestra el error al no complir con el password
            tiepassword.setError(getString(R.string.errPassword));
            displaySoftKeyboard(tiepassword);//Devolvemos el foco
            return false;
        }

            Log.e("Error password","PASSWROD SI CUMPLE");
            return true;

    }
    /*Valida el Email introducido en la activity*/
    private boolean ValidateEmail(String email) {

        //1. validar el patron del email(lo realizaremos de dos formas, usando la herramienta de android y de forma manual por nosotros mismos)
        //2. y validarlo con un correo (En  un futuro)
        if(CommonUtils.CheckPatternEmail(email)) {

            Log.e("Error Email","EMAIL SI CUMPLE");
            return true;
        }
        else {
            //Muentra el error
            tieemail.setError(getString(R.string.errEmail));
            displaySoftKeyboard(tieemail);//Devolvemos el foco
            Log.e("Error Email", "EMAIL NO CUMPLE");
            return false;
        }
    }

//TODO Aun no valida cuando sin hacer click en  sigUp(debe mostrar n o valido en cuanto no cumple un patron sin dar a click
    /*Clase creada para  Borrar el error en el tild de cada componente en el momento concreto que se cumple su validate,
    (no siempre en cuanto  fallo una vez)*/
    class  SingUpWatcher implements TextWatcher{

        private  View view;
        public  SingUpWatcher(View view){
            this.view=view;

        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            switch (view.getId())
            {
                case(R.id.tieNameUser) :
                    ValidateUser(((EditText)view).getText().toString());

                    break;
                case(R.id.tiepassword) :
                    ValidatePassword(((EditText)view).getText().toString());

                    break;
                case(R.id.tieemail) :
                    ValidateEmail(((EditText)view).getText().toString());

                    break;

            }

        }
    }
    /*En caso de que un componente muestre un error este, recibe el foco */
    private  void displaySoftKeyboard(View view)
    {
        if(view.requestFocus()){
            //Muestra el teclado al recibir el foco tras un error
            ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view,0);
        }

    }
}

