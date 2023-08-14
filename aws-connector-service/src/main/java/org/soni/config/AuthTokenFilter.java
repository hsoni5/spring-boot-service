package org.soni.config;


@Log4j2
public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            log.debug("Start AuthTokenFilter doFilterInternal");
            UserDto gfUserDto = jwtUtil.parseToken(request.getHeader(CommonConstant.X_OM_DT));
            if (ObjectUtils.isNotEmpty(gfUserDto)) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(gfUserDto);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception exception) {
            log.error("Cannot set user authentication: {}", exception);
        }
        log.debug("End AuthTokenFilter doFilterInternal");
        filterChain.doFilter(request, response);
    }

}