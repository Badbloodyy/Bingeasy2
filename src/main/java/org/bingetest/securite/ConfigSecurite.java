package org.bingetest.securite;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;


@EnableWebSecurity
public class ConfigSecurite extends WebSecurityConfigurerAdapter 
{
		
	// Recuperer des données d'application properties
	
	/* @Value("${spring.security.user.name}") // On récupére les données dans application properties grace à cette commande (value + $)
	private String userName;
	
	@Value("${spring.security.user.password}")
	private String motdepasse;
	
	@Value("${camembert}")
	private String fromagenul; */
	
	
	// -----------------------------------------
	
	// Manière d'authentification via inMemory
	
	
	// surcharger L'authentification
		
	
	/* @Override   
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication() // on donne les droits et les pass/log des utilisateurs.
		.withUser("julien")               // On va pas trop utuliser ça dans notre application actuelle au profit 
		.password("root")               // du jbcd, jdcb? Bref, au profit de ça.
		.roles("ADMIN")
		.and()
		.withUser("user")
		.password("root")
		.roles("USER")
		;} 
	*/
	
	
	// --------------------------------
	
	// Authentification JDBC
	
	/* @Autowired // Sert pour la partie jdbc
	DataSource dataSource;
	
	// Surcharger l'authentification
	
	@Override   
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select login, motdepasse, actif " + // C'est de la "good practice" de mettre si un utilisateurs est actif ou non
		"from utilisateur " +            // Donc mettre actif ou pas, ça permet de savoir si ça lui permet de connecter ou non (genre un gars de   
		"where login = ?")       // l'entreprise est viré.
		.authoritiesByUsernameQuery("select u.login, r.nom " +
		"from role r " +               
		"join utilisateur_role ur on r.id = ur.id_role " +               
		"join utilisateur u on u.id = ur.id_utilisateur " +               
		"where u.login = ?");
	}
	*/
	
	// ---------------------------------------------
	
	
		// Authentification via JPA
	
	
	@Autowired 
	MonUserDetailService userDetailsService;
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;

	@Override   
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService); 

	}
	
	
	
	
	// surcharger L'authorisation
		
		@Override protected void configure(HttpSecurity http) throws Exception {
		http
		.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
		.and().httpBasic()
		.and().authorizeRequests()
        .antMatchers("/admin/**").hasRole("ADMIN")  // ** signifie "n'importe quoi derriere". On pourra garder toute cette partie la pour la suite
        .antMatchers("/user/**").hasAnyRole("USER", "ADMIN") // lorsqu'on passera par jdbc. Le ** dit que toute les sous adresse de user necessiterons ce rôle
        .antMatchers("/authentification", "/inscription", "/hello").permitAll() // La partie /admin/xx est accessible par le role admin, /user/xx par un user et un admin, / par le reste.
        // .and()
        // .formLogin(); 
        // login accessible par tout le monde. On l'enleve pour ne plus avoir le login.
		 .anyRequest().authenticated()           
		 .and().exceptionHandling()           
		 .and().sessionManagement()           
		 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		 .and().csrf().disable();
		   http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); 
		}
		
	
	
	@SuppressWarnings("deprecation")
	@Bean   
	 public PasswordEncoder getPasswordEncoder(){
		 // return NoOpPasswordEncoder.getInstance();   // N'est pas vraiment déprécié en fait. Ca sert a dire a spring qu'on a un mdp en clair.
		 // On dit qu'un truc est deprecated car on va bientot l'enlever mais ici c'est utilisé pour dire qu'il faut pas l'utiliser.
		return new BCryptPasswordEncoder(); // Cette méthode surcharge notre bean. A chaque fois qu'on recupere un mdp de
		// l'user, on va l'encrypter. Si on nous donne root, bcrypt va l'encrypter et le comparer avec ce qu'il y a dans la
		// base de donnée. Le soucis c'est que les mdp dans la db sont non crypté donc va falloir les crypter aussi.
	} 
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

