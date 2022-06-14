import React, { useEffect } from 'react'
import profilePic from '../../assets/profilePic.png'
import './profile.scss'

function ProfileHeader() {
	const [isSmallFormFactor, setIsSmallFormFactor] = React.useState(window.innerWidth < 735)

    useEffect(() => {
      function handleResize() {
        setIsSmallFormFactor(window.innerWidth < 735);
      }

      window.addEventListener('resize', handleResize)

      return (() => {
        window.removeEventListener('resize', handleResize)
      })
    })

    function renderPostAndFollowsContainer() {
      return (
        <ul>
          <li className='posts-and-follows inline'><b>22</b> posts</li>
          <li className='posts-and-follows inline'><b>1,069</b> folowers</li>
          <li className='posts-and-follows inline'><b>988</b> following</li>
        </ul>
      )
    }

	return (
		<div id='profileHeaderContainer'>
			<div id='profilePic'>
				<img id='profilePic' src={profilePic} alt='profilePic' />
			</div>
			<div id='profileInfo'>
				<div id='profileInfoFirstRow'>
					<h1 id='username'>loopy_lax</h1>
					<button id='editProfileButton'> Edit Profile </button>
				</div>
				<div id='postsAndFollowsContainer'>
					{!isSmallFormFactor && renderPostAndFollowsContainer()}
				</div>
				<div>University of Pennsylvania Alum</div>
				<div id='postsAndFollowsContainerSmall'>
					{isSmallFormFactor && renderPostAndFollowsContainer()}
				</div>
			</div>
		</div>
	)
}

export default ProfileHeader;